import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";

export default function TopicCard({ topic, onClickStartTopic }) {
  return (
    <Card style={{ width: "15rem" }}>
      <Card.Body>
        <Card.Title>{topic?.name}</Card.Title>
        <Button
          variant="success"
          className="me-2 mt-2"
          type="button"
          onClick={() => onClickStartTopic(topic, true)}
        >
          Iniciar votação
        </Button>
      </Card.Body>
    </Card>
  );
}
