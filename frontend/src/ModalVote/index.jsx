import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

export default function ModalVote(props) {
  return (
    <Modal
      show={props.showModalVote}
      onHide={() => props.setShowModalVote(false)}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      <Modal.Header closeButton>
        <Modal.Title id="contained-modal-title-vcenter">Votar</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          <Form.Group
            className="mb-3"
            id="formGridCheckbox"
            value={props.topicVote}
            onChange={(e) => props.setTopicVote(e.target.value)}
          >
            <Form.Label as="legend" column>
              {props?.topic}
            </Form.Label>
            <Form.Check
              type="radio"
              label="Sim"
              name="formTopicResponse"
              value="SIM"
            />
            <Form.Check
              type="radio"
              label="Não"
              name="formTopicResponse"
              value="NAO"
            />
          </Form.Group>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button
          variant="secondary"
          onClick={() => props.setShowModalVote(false)}
        >
          Cancelar
        </Button>
        <Button variant="primary" onClick={props.onSubmitVote}>
          Votar
        </Button>
      </Modal.Footer>
    </Modal>
  );
}
