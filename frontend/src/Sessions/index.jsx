import Button from "react-bootstrap/Button";
import ButtonGroup from "react-bootstrap/ButtonGroup";
import SessionCard from "../SessionCard";
import { useEffect } from "react";
import { api } from "../utils/api";

export default function Sessions({
  sessions,
  setSessions,
  onClickVote,
  closedVoting,
  setClosedVoting,
}) {
  useEffect(() => {
    api
      .get("sessions", {
        params: { showClosedSessions: closedVoting ? "Y" : "N" },
      })
      .then((result) => {
        setSessions(result.data);
      });
  }, [closedVoting, setSessions]);

  function onClickButton(session) {
    onClickVote(session);
  }

  return (
    <section>
      <header className="align-end">
        <ButtonGroup aria-label="Basic example">
          <Button
            variant="light"
            className="me-1"
            active={!closedVoting}
            onClick={() => setClosedVoting(false)}
          >
            Votações Ativas
          </Button>
          <Button
            variant="light"
            active={closedVoting}
            onClick={() => setClosedVoting(true)}
          >
            Votações Encerradas
          </Button>
        </ButtonGroup>
      </header>

      <div className="cards-container">
        {sessions?.map((session) => (
          <SessionCard
            key={`${session.id}-session`}
            session={session}
            closedVoting={closedVoting}
            onClickButton={onClickButton}
          />
        ))}
      </div>
    </section>
  );
}
