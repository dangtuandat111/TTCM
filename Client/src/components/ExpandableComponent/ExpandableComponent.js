import React, { useState, useRef, useCallback } from "react";
import DataTable from "react-data-table-component";
import EditableCell from "components/EditableCell/EditableCell";
import DateTimePicker from "react-datetime-picker";
import Filter from "components/Filter/Filter";
import moment from "moment";
import Local from "../../services/local.service"
const ExpandableComponent = ({ data, page }) => {
  const [expandableData, setExpandableData] = React.useState([]);
  const [editingId, setEditingId] = useState("");
  const [resetPaginationToggle, setResetPaginationToggle] = React.useState(
    false
  ); // reset to page 1
  let formData = useRef({}).current;
  const isEditing = (record) => record.id === editingId;
  const [filter, setFilter] = useState("");
  const filteredItemProduct = expandableData
    .filter((item) => {
      const time = moment(filter).format("YYYY-MM-DD");
      if (time == item.import_date) {
        return item;
      }
      if (filter === "") {
        return item;
      }
    });
  const filterItemImportAndExport = expandableData.filter(
    (item) =>
      item.product && item.product.toLowerCase().includes(filter.toLowerCase())
  );
  const [columns, setColums] = React.useState([]);
  React.useEffect(() => {
    if (page == "products") {
      setColums([
        {
          name: "ID",
          selector: "id",
          sortable: true,
        },
        {
          name: "SỐ LƯỢNG",
          selector: "quantity",
          sortable: true,
        },
        {
          name: "GIÁ NHẬP",
          selector: "cost",
          sortable: true,
        },
        {
          name: "NGÀY HẾT HẠN",
          selector: "expriy_date",
          sortable: true,
        },
        {
          name: "TRẠNG THÁI",
          selector: "status",
          sortable: true,
          editable: true,
        },
      ]);
    } else if (page === "importOrExport-products") {
      setColums([
        {
          name: "SẢN PHẨM",
          selector: "product",
          sortable: true,
          editable: true,
        },
        {
          name: "SỐ LƯỢNG",
          selector: "quantity",
          sortable: true,
          editable: true,
        },
        {
          name: "GÍA",
          selector: "cost",
          sortable: true,
          editable: true,
        },
      ]);
    }
  }, []);

  React.useEffect(() => {
    async function getData() {
      if(page == "products"){
        const product_details = await Local.getById("products", data.id)
        setExpandableData(product_details.data)
      }
      else if(page == "importOrExport-products") {
        const bill_details = await Local.getById("exports", data.id);
        const tempData = bill_details.data.map(item => {
          return {
            product: item.product.name,
            quantity: item.quantity,
            cost: item.cost,
          }
        });
        setExpandableData(tempData);
      }
    }
    getData()
  }, [])

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
    const payload = { ...item, ...formData};
 
    
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

  /* Start filter table */
  const subHeaderComponentProducts = React.useMemo(() => {
    const handleChange = (value) => {
      if (value == null) {
        setFilter("");
        return;
      }
      setFilter(value);
      console.log(data);
    };
    return (
      <>
        <DateTimePicker onChange={handleChange} value={filter} />
      </>
    );
  }, [filter]);
  /* End filter table */

  /* Start filter table */
  const subHeaderComponentImportAndExport = React.useMemo(() => {
    const handleClear = () => {
      if (filter) {
        setResetPaginationToggle(!resetPaginationToggle);
        setFilter("");
      }
    };
    return (
      <>
        <Filter
          onFilter={(e) => setFilter(e.target.value)}
          onClear={handleClear}
          filterText={filter}
        />
      </>
    );
  }, [filter]);
  /* End filter table */

  return (
    <>
      <DataTable
        columns={createColumns()}
        data={page === "products" ? filteredItemProduct : filterItemImportAndExport}
        className="table mb-5"
        subHeader
        subHeaderComponent={
          page === "products"
            ? subHeaderComponentProducts
            : subHeaderComponentImportAndExport
        }
      />
      \
    </>
  );
};
export default ExpandableComponent;
