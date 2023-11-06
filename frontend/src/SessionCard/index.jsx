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
      {closedVoting ? (
        <Card.Subtitle className="ps-4">Resultado:</Card.Subtitle>
      ) : (
        <ListGroup className="list-group-flush">
          <ListGroup.Item>
            Data de início: {formatDate(session?.openingDate)}
          </ListGroup.Item>
          <ListGroup.Item>
            Data de encerramento: {formatDate(session?.closingDate)}
          </ListGroup.Item>
        </ListGroup>
      )}
      <Card.Body>
        {!closedVoting ? (
          <Button
            variant="primary"
            className="mt-2"
            onClick={() => onClickButton(session)}
          >
            Votar
          </Button>
        ) : (
          <ListGroup className="list-group-flush">
            <ListGroup.Item>Sim: {session.result ? session?.result["SIM"] : 0}</ListGroup.Item>
            <ListGroup.Item>Não: {session.result ? session?.result["NAO"] : 0}</ListGroup.Item>
          </ListGroup>
        )}
      </Card.Body>
    </Card>
  );
}
