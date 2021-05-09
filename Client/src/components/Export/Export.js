import {Button} from "reactstrap"
const Export = ({ onExport }) => (
    <Button onClick={e => onExport(e.target.value) } className="btn-fill btn-wd btn-info"><i class="fas fa-file-export"></i> Export</Button>
);
export default Export