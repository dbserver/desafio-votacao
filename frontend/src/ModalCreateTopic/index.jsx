import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

export default function ModalCreateTopic(props) {
  return (
    <Modal
      show={props.showModalCreateTopic}
      onHide={() => props.setShowModalCreateTopic(false)}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      <Modal.Header closeButton>
        <Modal.Title id="contained-modal-title-vcenter">
          Cadastrar uma nova pauta
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Pauta</Form.Label>
            <Form.Control
              type="text"
              placeholder="Pauta"
              autoFocus
              value={props.newTopic}
              onChange={(e) => props.setNewTopic(e.target.value)}
            />
          </Form.Group>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button
          variant="secondary"
          onClick={() => props.setShowModalCreateTopic(false)}
        >
          Cancelar
        </Button>
        <Button variant="primary" onClick={props.onCreateNewTopic}>
          Cadastrar
        </Button>
      </Modal.Footer>
    </Modal>
  );
}
