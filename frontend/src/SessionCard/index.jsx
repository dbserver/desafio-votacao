import Card from "react-bootstrap/Card";
import Button from "react-bootstrap/Button";
import ListGroup from "react-bootstrap/ListGroup";
import { formatDate } from "../utils/utils";

export default function SessionCard({ session, closedVoting, onClickButton }) {
  return (
    <Card style={{ width: "18rem" }}>
      <Card.Body>
        <Card.Title>{session?.topic?.name}</Card.Title>
      </Card.Body>
      <ListGroup className="list-group-flush">
        <ListGroup.Item>
          Data de início: {formatDate(session?.openingDate)}
        </ListGroup.Item>
        <ListGroup.Item>
          Data de encerramento: {formatDate(session?.closingDate)}
        </ListGroup.Item>
      </ListGroup>
      <Card.Body>
        <Button
          variant="primary"
          className="mt-2"
          onClick={() => onClickButton(session)}
        >
          {closedVoting ? "Ver resultado" : "Votar"}
        </Button>
      </Card.Body>
    </Card>
  );
}
