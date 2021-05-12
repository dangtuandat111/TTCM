import React, { useState } from "react";
const EditableCell = ({ row, index, column, col, onChange, options, page }) => {
  const [value, setValue] = useState(row[column.selector]);
  const [optionValue, setOptionValue] = useState("");
  const handleOnChange = (e) => {
    setValue(e.target.value);
    onChange?.(e);
  };
  React.useEffect(() => {
    async function getData() {
      if (page === "export-products") {
        setOptionValue(row.store);
      } else if (page === "import-products") {
        setOptionValue(row.supplier);
      } else if (page === "products") {
        setOptionValue(row.category);
      } else {
        setOptionValue("");
      }
    }
    getData();
  }, []);

  function handleChange(event) {
    setOptionValue(event.target.value);
  }

  if (column.optioning) {
    return (
      <select
        value={optionValue}
        onChange={handleChange}
        name={column.selector}
        disabled={column.editing ? false : true}
      >
        {options.map((option, index) => {
          return (
            <option value={option.id} key={index}>
              {option.name}
            </option>
          );
        })}
      </select>
    );
  }

  if (column?.editing) {
    return (
      <input
        type={column.type || "text"}
        name={column.selector}
        style={{ width: "100%" }}
        onChange={handleOnChange}
        value={column.type  ? "" : value}
      />
    );
  }

  if (col.cell) {
    return col.cell(row, index, column);
  }
  return row[column.selector];
};

export default EditableCell;
