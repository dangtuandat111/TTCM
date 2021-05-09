import React, { Component } from "react";
import styled from 'styled-components';
import {Button} from 'react-bootstrap'
const TextField = styled.input`
height: 32px;
width: 200px;
border-radius: 3px;
border-top-left-radius: 5px;
border-bottom-left-radius: 5px;
border-top-right-radius: 0;
border-bottom-right-radius: 0;
border: 3px solid #e5e5e5;
padding: 0 32px 0 16px;

&:hover {
.add('Show Table Head', () => <ProgressPendingIndeterminateHeader />);
  cursor: pointer;
}
`;

const ClearButton = styled(Button)`
border-top-left-radius: 0;
border-bottom-left-radius: 0;
border-top-right-radius: 5px;
border-bottom-right-radius: 5px;
height: 34px;
width: 32px;
text-align: center;
display: flex;
align-items: center;
justify-content: center;
`;
const Filter = ({ filterText, onFilter, onClear }) => (
    <>
      <TextField id="search" type="text" placeholder="Filter By Name" aria-label="Search Input" value={filterText} onChange={onFilter} />
      <ClearButton type="button" className="btn-fill btn-danger" onClick={onClear}>X</ClearButton>
    </>
);

export default Filter