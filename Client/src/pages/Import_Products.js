import React, { useState, useRef, useCallback } from "react";
import DataTable from "react-data-table-component";
import EditableCell from "components/EditableCell/EditableCell";
import ExpandableComponent from "components/ExpandableComponent/ExpandableComponent";
import Filter from "components/Filter/Filter";
import Export from "components/Export/Export";
import { Add, Delete } from "components/CRUD/Index";
import { Button, Modal, Form, Spinner } from "react-bootstrap";
import { useForm } from "react-hook-form";
import Local from "services/local.service";
const customStyles = {
  rows: {
    style: {
      minHeight: "72px", // override the row height
    },
  },
  headCells: {
    style: {
      paddingLeft: "8px", // override the cell padding for head cells
      paddingRight: "8px",
    },
  },
  cells: {
    style: {
      paddingLeft: "8px", // override the cell padding for data cells
      paddingRight: "8px",
    },
  },
  columns: {
    style: {
      fontFamily: "Roboto-Bold",
      fontSize: "14px",
      Color: "#333",
      lineHeight: 1.4,
    },
  },
};
const columns = [
  {
    name: "ID",
    selector: "id",
    sortable: true,
  },
  {
    name: "NHÀ CUNG CẤP",
    selector: "supplier",
    sortable: true,
    editable: true,
  },
  {
    name: "NGÀY NHẬP",
    selector: "import_date",
    editable: true,
  },
  {
    name: "TỔNG HÓA ĐƠN",
    selector: "total_cost",
    sortable: true,

  },
  {
    name: "TRẠNG THÁI",
    selector: "status",
    sortable: true,
    editable: true,
  },
];

const columnsOfAdd = [
  {
    name: "ID",
    selector: "id",
  },
  {
    name: "SẢN PHẨM",
    selector: "name",
  },
  {
    name: "SỐ LƯỢNG",
    selector: "quantity",
    editable: true,
  },
];
export default function Import_Products() {
  const [data, setData] = React.useState([]);
  const [supplier, setSupplier] = React.useState([]);
  const [product, setProduct] = React.useState([{
    id: 2,
    name: "Cá ngừ"
  }]);
  const [filterText, setFilterText] = React.useState(""); // text in filter input
  const filteredItems = data.filter(
    (item) =>
      item.supplier && item.supplier.toLowerCase().includes(filterText.toLowerCase())
  ); // filter by name
  const [receiveProduct, setReceiveProduct] = React.useState({});
  const [innerData, setInnerData] = useState(filteredItems);
  const [editingId, setEditingId] = useState("");
  let formData = useRef({}).current;
  const isEditing = (record) => record.id === editingId;
  const [resetPaginationToggle, setResetPaginationToggle] = React.useState(
    false
  ); // reset to page 1
  const [toggledClearRows, setToggledClearRows] = React.useState(false);
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const [isLoading, setIsLoading] = useState(false);
  const [nextButton, SetNextButton] = React.useState(true);
  const { register, errors, handleSubmit } = useForm();
  const actionExport = React.useMemo(
    () => <Export onExport={() => downloadCSV(data)} />,
    []
  ); // export selected item

  const actionDelete = React.useMemo(
    () => <Delete onDelete={() => deleteOfSelected()} />,
    [toggledClearRows]
  ); // delete selected item

  const actionAdd = React.useMemo(() => <Add onAdd={handleShow} />, []); // add new item

  React.useEffect(() => {
    async function getData() {
      const inBills = await Local.getAll();
      const suppliers = await Local.getAll();
      setData(inBills);
      setSupplier(suppliers);
    }
  })

  /* Start edit row */
  const formOnChange = (event) => {
    const nam = event.target.name;
    const val = event.target.value;

    formData = {
      ...formData,
      [nam]: val,
    };
  };

  const edit = (record) => {
    setEditingId(record.id);
  };

  const cancel = () => {
    setEditingId("");
  };

  const save = (item) => {
    const payload = { ...item, ...formData };
    if (payload.name != null) {
      const tempData = [...receiveProduct.products];

      const index = tempData.findIndex((item) => editingId === item.id);
      if (index > -1) {
        const item = tempData[index];
        tempData.splice(index, 1, {
          ...item,
          ...payload,
        });
        setEditingId("");
        setReceiveProduct({ ...receiveProduct, products: tempData });
      }
    } else {
      console.log(payload);
      setEditingId("");
      const sendData = {
        id: 1,
        status: "updated",
      };
      // Local.update("/inbill", payload.id, sendData)
      //   .then(() => {
      //     alert("Updated Success");
      //   })
      //   .catch((error) => {
      //     console.log(error);
      //   });
    }
  };

  const mergedColumns = columns.map((col) => {
    if (!col.editable) {
      return col;
    }
    return {
      ...col,
      cell: (row, index, column) => {
        const editing = isEditing(row);
        return (
          <EditableCell
            row={row}
            index={index}
            column={{ ...column, editing }}
            col={col}
            onChange={formOnChange}
            options={supplier}
            page="import-products"
          />
        );
      },
    };
  });

  const mergedColumnsOfAdd = columnsOfAdd.map((col) => {
    if (!col.editable) {
      return col;
    } else if (col.option) {
      return {
        ...col,
        cell: (row, index, column) => {
          const editing = isEditing(row);
          const optioning = true;
          return (
            <EditableCell
              row={row}
              index={index}
              column={{ ...column, editing, optioning }}
              col={col}
              onChange={formOnChange}
              page="export-products"
              options={store}
            />
          );
        },
      };
    }
    return {
      ...col,
      cell: (row, index, column) => {
        const editing = isEditing(row);
        return (
          <EditableCell
            row={row}
            index={index}
            column={{ ...column, editing }}
            col={col}
            onChange={formOnChange}
          />
        );
      },
    };
  });

  const createColumns = useCallback((columns) => {
    return [
      ...columns,
      {
        name: "ACTIONS",
        allowOverflow: true,
        minWidth: "200px",
        cell: (row) => {
          const editable = isEditing(row);
          if (editable) {
            return (
              <div>
                <button
                  type="button"
                  onClick={() => save(row)}
                  style={{ backgroundColor: "lightgreen" }}
                >
                  save
                </button>
                <button
                  type="button"
                  onClick={cancel}
                  style={{ backgroundColor: "orangered" }}
                >
                  cancel
                </button>
              </div>
            );
          }
          return (
            <button
              type="button"
              onClick={() => edit(row)}
              style={{ backgroundColor: "aliceblue" }}
            >
              edit
            </button>
          );
        },
      },
    ];
  }, [mergedColumns]);
  /* End edit row */

  /* Start filter table */
  const subHeaderComponentMemo = React.useMemo(() => {
    const handleClear = () => {
      if (filterText) {
        setResetPaginationToggle(!resetPaginationToggle);
        setFilterText("");
      }
    };

    return (
      <>
        <Filter
          onFilter={(e) => setFilterText(e.target.value)}
          onClear={handleClear}
          filterText={filterText}
        />
      </>
    );
  }, [filterText, resetPaginationToggle]);
  /* End filter table */

  /* Start export SpreadSheet */
  function convertArrayOfObjectsToCSV(array) {
    let result;

    const columnDelimiter = ",";
    const lineDelimiter = "\n";
    const keys = Object.keys(data[0]);

    result = "";
    result += keys.join(columnDelimiter);
    result += lineDelimiter;

    array.forEach((item) => {
      let ctr = 0;
      keys.forEach((key) => {
        if (ctr > 0) result += columnDelimiter;

        result += item[key];

        ctr++;
      });
      result += lineDelimiter;
    });

    return result;
  }

  function downloadCSV(array) {
    const link = document.createElement("a");
    let csv = convertArrayOfObjectsToCSV(array);
    if (csv == null) return;

    const filename = "export.csv";

    if (!csv.match(/^data:text\/csv/i)) {
      csv = `data:text/csv;charset=utf-8,${csv}`;
    }

    link.setAttribute("href", encodeURI(csv));
    link.setAttribute("download", filename);
    link.click();
  }
  /* End export SpreadSheet */

  function deleteOfSelected() {
    setToggledClearRows(!toggledClearRows);
    console.log(toggledClearRows);
  }

  function addInvoice(data) {
    SetNextButton(false);
    const choseProducts = data.products.map((prop, index) => {
      return ({
        id: prop,
        name: product.find(item => item.id == prop).name,
        quantity: 0,
      });
    })
    setReceiveProduct({
      products: choseProducts,
      supplier: data.supplier,
    });
  }

  function handleBack() {
    setReceiveProduct({});
    SetNextButton(true);
  }

  function handleCreate() {
    setIsLoading(true)
    console.log(receiveProduct);
    // Local.create("intbill", receiveProduct, page = "import")
    //   .then(() => {
    //     handleClose(true),
    //     setIsLoading(false)
    //     alert("File Updated Success");
    //   })
    //   .catch((err) => console.log(err));
  }


  return (
    <>
      <DataTable
        title="HÓA ĐƠN NHẬP"
        columns={createColumns(mergedColumns)}
        data={filteredItems}
        defaultSortField="name"
        keyField="id"
        highlightOnHover
        responsive
        customStyles={customStyles}
        selectableRows
        clearSelectedRows={toggledClearRows}
        expandableRows
        expandableRowsComponent={<ExpandableComponent page={"importOrExport-products"}/>}
        pagination
        paginationResetDefaultPage={resetPaginationToggle} // optionally, a hook to reset pagination to page 1
        subHeader
        subHeaderComponent={[subHeaderComponentMemo]}
        actions={[actionExport, actionAdd]}
        noContextMenu
        persistTableHead
      />
     <Modal show={show} onHide={handleClose} size="lg">
        <Modal.Header closeButton>
          <Modal.Title>Thêm hóa đơn xuất</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {nextButton ? (
            <Form onSubmit={handleSubmit(addInvoice)} id="chooseProducts">
              <Form.Group>
                <Form.Label>Sản phẩm</Form.Label>
                <Form.Control
                  as="select"
                  multiple
                  name="products"
                  {...register("products")}
                >
                  <option value="2">1</option>
                  <option>2</option>
                  <option>3</option>
                  <option>4</option>
                  <option>5</option>
                </Form.Control>
              </Form.Group>
              <Form.Group>
                <Form.Label>Nhà cung cấp</Form.Label>
                <Form.Control as="select" name="supplier" {...register("store")}>
                  <option>1</option>
                  <option>2</option>
                  <option>3</option>
                  <option>4</option>
                  <option>5</option>
                </Form.Control>
              </Form.Group>
            </Form>
          ) : (
            <DataTable
              columns={createColumns(mergedColumnsOfAdd)}
              data={receiveProduct.products}
            />
          )}
        </Modal.Body>
        <Modal.Footer>
          <Button
            variant="danger btn-fill"
            onClick={nextButton ? handleClose : handleBack}
          >
            {nextButton ? "Đóng" : "Quay lại"}
          </Button>
          <Button
            variant="info btn-fill"
            type={nextButton ? "submit" : "button"}
            form={nextButton ? "chooseProducts" : ""}
            onClick={nextButton ? null : handleCreate}
          >
            {isLoading ? <Spinner animation="border" role="status" size="sm"/> : ""}
            {nextButton ? "Tiếp tục" : "Tạo hóa đơn"}
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}
