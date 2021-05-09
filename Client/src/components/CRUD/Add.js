import {Button} from "reactstrap"
const Add = ({ onAdd }) => (
    <Button onClick={e => onAdd(e.target.value) } className="btn-fill btn-wd btn-success"><i className="fas fa-plus"></i> Add</Button>
);
export default Add