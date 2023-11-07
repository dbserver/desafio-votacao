
import Modal from "react-bootstrap/Modal";

export default function CenteredModal(props) {
  return (
    <Modal
      {...props}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      {props?.children}
      
    </Modal>
  );
}
