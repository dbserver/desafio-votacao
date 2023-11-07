import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";

export default function ModalResult(props) {
  return (
    <Modal
      show={props.showModalResult}
      onHide={() => props.setShowModalResult(false)}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      <Modal.Header closeButton>
        <Modal.Title id="contained-modal-title-vcenter">
          Resultado da votação
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <div>
          <strong>Sim: </strong>
          <span>{props?.topic?.result ? props?.topic?.result["SIM"] : 0}</span>
        </div>
        <div>
          <strong>Não: </strong>
          <span>{props?.topic?.result ? props?.topic?.result["NAO"] : 0}</span>
        </div>
      </Modal.Body>
      <Modal.Footer>
        <Button
          variant="primary"
          onClick={() => props.setShowModalResult(false)}
        >
          Voltar
        </Button>
      </Modal.Footer>
    </Modal>
  );
}
