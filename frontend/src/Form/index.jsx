import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import "./styles.css";

export default function LoginForm({ cpf, handleCpf, onSubmit }) {
  return (
    <div id="form-container">
      <Form id="form-wrapper" onSubmit={() => onSubmit()}>
        <Form.Group className="mb-3">
          <Form.Label>CPF</Form.Label>
          <Form.Control
            type="text"
            placeholder="Digite o seu CPF"
            value={cpf}
            onChange={handleCpf}
          />
        </Form.Group>
        <Button variant="primary" onClick={() => onSubmit()}>
          Entrar
        </Button>
      </Form>
    </div>
  );
}
