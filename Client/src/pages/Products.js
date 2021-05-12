import React, { useState, useRef, useCallback } from "react";
import DataTable from "react-data-table-component";
import EditableCell from "components/EditableCell/EditableCell";
import ExpandableComponent from "components/ExpandableComponent/ExpandableComponent";
import Filter from "components/Filter/Filter";
import Export from "components/Export/Export";
import { Add, Delete } from "components/CRUD/Index";
import { Button, Modal, Form, Spinner } from "react-bootstrap";
import Local from "../services/local.service";
import { useForm } from "react-hook-form";
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
    name: "SẢN PHẨM",
    selector: "name",
    sortable: true,
    editable: true,
  },
  {
    name: "LOẠI",
    selector: "category",
    option: true,
    editable: true,
  },
  {
    name: "TRẠNG THÁI",
    selector: "status",
    editable: true,
  },
  {
    name: "ẢNH",
    selector: "image",
    grow: 0,
    type: "file",
    editable: true,
    cell: (row) => (
      <img height="84px" width="56px" alt={row.name} src={row.image} />
    ),
  },
  {
    name: "MÔ TẢ",
    selector: "description",
    editable: true,
  },
];

export default function Products() {
  const [data, setData] = React.useState([]);
  const [category, setCategory] = React.useState([]);
  const [filterText, setFilterText] = React.useState(""); // text in filter input
  const filteredItems = data.filter(
    (item) =>
      item.name && item.name.toLowerCase().includes(filterText.toLowerCase())
  ); // filter by name
  const { register, errors, handleSubmit } = useForm();
  const [editingId, setEditingId] = useState("");
  let formData = useRef({}).current;
  const isEditing = (record) => record.id === editingId;
  const [resetPaginationToggle, setResetPaginationToggle] = React.useState(
    false
  ); // reset to page 1
  const [toggledClearRows, setToggledClearRows] = React.useState(false);
  const [show, setShow] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const actionExport = React.useMemo(
    () => <Export onExport={() => downloadCSV(data)} />,
    []
  ); // export selected item

  React.useEffect(() => {
    async function getData() {
      const products = await Local.getAll("/products");
      const categories = await Local.getAll("/categories");
      const tempData = products.data.map((item) => {
        return {
          id: item.id,
          name: item.name,
          category: item.category.id,
          status: item.status,
          image: item.image,
          description: item.description,
        };
      });
      console.log(products);
      setData(tempData);
      setCategory(categories.data);
    }
    getData();
  }, [isLoading]);

  const actionDelete = React.useMemo(
    () => <Delete onDelete={() => deleteOfSelected()} />,
    [toggledClearRows]
  ); // delete selected item

  const actionAdd = React.useMemo(() => <Add onAdd={handleShow} />, []); // add new item

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
    const tempData = { ...item, ...formData };
    setEditingId("");
    const sendData = {
      name: tempData.name,
      category_id: tempData.category,
      status: "updated",
      description: tempData.description,
    };
    console.log(tempData);
    // const formData = new FormData();
    // formData.append("sendData", JSON.stringify(sendData));
    // formData.append("image", data.image[0]);
    // Local.create("/products", formData, "products")
    //   .then(() => {
    //     setIsLoading(false);
    //     handleClose(true);
    //     alert("Created Success");
    //   })
    //   .catch((error) => {
    //     console.log(error);
    //   });
    // Local.update("/products", payload.id, sendData)
    //   .then(() => {
    //     alert("Updated Success");
    //   })
    //   .catch((error) => {
    //     console.log(error);
    //   });
  };

  const mergedColumns = columns.map((col) => {
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
              options={category}
              page="products"
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
            options={category}
            page="products"
          />
        );
      },
    };
  });

  const createColumns = useCallback(() => {
    return [
      ...mergedColumns,
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

  function addProduct(data) {
    setIsLoading(true);
    const sendData = {
      name: data.name,
      category_id: data.category,
      status: "new",
      description: data.description,
    };
    const formData = new FormData();
    formData.append("sendData", JSON.stringify(sendData));
    formData.append("image", data.image[0]);
    Local.create("/products", formData, "products")
      .then(() => {
        setIsLoading(false);
        handleClose(true);
        alert("Created Success");
      })
      .catch((error) => {
        console.log(error);
      });
  }

  return (
    <>
      <DataTable
        title="DANH SÁCH SẢN PHẨM"
        columns={createColumns()}
        data={filteredItems}
        defaultSortField="name"
        keyField="id"
        highlightOnHover
        responsive
        customStyles={customStyles}
        selectableRows
        clearSelectedRows={toggledClearRows}
        expandableRows
        expandableRowsComponent={<ExpandableComponent page={"products"} />}
        pagination
        paginationResetDefaultPage={resetPaginationToggle} // optionally, a hook to reset pagination to page 1
        subHeader
        subHeaderComponent={[subHeaderComponentMemo]}
        actions={[actionExport, actionAdd]}
        noContextMenu
        persistTableHead
      />
      <Modal show={show} onHide={handleClose}>
        <Form onSubmit={handleSubmit(addProduct)}>
          <Modal.Header closeButton>
            <Modal.Title>Thêm sản phẩm</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Form.Group>
              <Form.Label>Loại</Form.Label>
              <Form.Control as="select" {...register("category")}>
                {category.map((item, index) => {
                  return (
                    <option key={index} value={item.id}>
                      {item.name}
                    </option>
                  );
                })}
              </Form.Control>
            </Form.Group>
            <Form.Group>
              <Form.Label>Sản phẩm</Form.Label>
              <Form.Control type="text" {...register("name")} />
            </Form.Group>
            <Form.Group>
              <Form.File label="Ảnh" {...register("image")} />
            </Form.Group>
            <Form.Group>
              <Form.Label>Mô tả</Form.Label>
              <Form.Control
                as="textarea"
                rows={3}
                {...register("description")}
              />
            </Form.Group>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="danger btn-fill" onClick={handleClose}>
              Đóng
            </Button>
            <Button variant="info btn-fill" type="submit">
              {isLoading ? (
                <Spinner animation="border" role="status" size="sm" />
              ) : (
                ""
              )}
              Thêm
            </Button>
          </Modal.Footer>
        </Form>
      </Modal>
    </>
  );
}
