import {Button} from "reactstrap"
const Delete = ({ onDelete }) => (
    <Button onClick={e => onDelete(e.target.value) } className="btn-fill btn-wd btn-danger"><i className="fas fa-trash-alt"></i> Delete</Button>
);
export default Delete