import React, { useState, useRef, useCallback } from "react";
import DataTable from "react-data-table-component";
import EditableCell from "components/EditableCell/EditableCell";
import ExpandableComponent from "components/ExpandableComponent/ExpandableComponent";
import Filter from "components/Filter/Filter";
import Export from "components/Export/Export";
import { Add, Delete } from "components/CRUD/Index";
import { Button, Modal, Form } from "react-bootstrap";
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
    name: "CỞ SỞ",
    selector: "name",
    sortable: true,
    editable: true,
  },
  {
    name: "ĐỊA CHỈ",
    selector: "address",
    editable: true,
  },
  {
    name: "SỐ ĐIỆN THOẠI",
    selector: "phoneNumber",
    sortable: true,

  },
];

export default function Import_Products() {
  const [data, setData] = React.useState([
    {
      id: 1,
      name: "Greed Hà Nội",
      address: "260 Hai Bà Trưng",
      phoneNumber: "0123466",
    },
    {
      id: 2,
      name: "Greed Sài Gòn",
      address: "260 Quận 1",
      phoneNumber: "0123466",
    },
  ]);
  const [filterText, setFilterText] = React.useState(""); // text in filter input
  const filteredItems = data.filter(
    (item) =>
      item.name && item.name.toLowerCase().includes(filterText.toLowerCase())
  ); // filter by name
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
  const actionExport = React.useMemo(
    () => <Export onExport={() => downloadCSV(data)} />,
    []
  ); // export selected item

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
    const payload = { ...item, ...formData };
    const tempData = [...innerData];

    const index = tempData.findIndex((item) => editingId === item.id);
    if (index > -1) {
      const item = tempData[index];
      tempData.splice(index, 1, {
        ...item,
        ...payload,
      });
      setEditingId("");
      setInnerData(tempData);
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

  return (
    <>
      <DataTable
        title="CƠ SỞ"
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
        expandableRowsComponent={<ExpandableComponent page={"import-products"}/>}
        pagination
        paginationResetDefaultPage={resetPaginationToggle} // optionally, a hook to reset pagination to page 1
        subHeader
        subHeaderComponent={[subHeaderComponentMemo]}
        actions={[actionExport, actionAdd]}
        noContextMenu
        persistTableHead
      />
       <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Thêm nhà cơ sở</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group controlId="formBasicEmail">
              <Form.Label>Tên cơ sở</Form.Label>
              <Form.Control type="text" />
            </Form.Group>
            <Form.Group controlId="formBasicEmail">
              <Form.Label>Địa chỉ</Form.Label>
              <Form.Control type="text" />
            </Form.Group>
            <Form.Group controlId="formBasicEmail">
              <Form.Label>Số điện thoại</Form.Label>
              <Form.Control type="text" />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary btn-fill" onClick={handleClose}>
            Close
          </Button>
          <Button
            variant="primary btn-fill"
            type="submit"
            onClick={handleClose}
          >
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}
