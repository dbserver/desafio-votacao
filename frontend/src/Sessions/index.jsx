import Button from "react-bootstrap/Button";
import ButtonGroup from "react-bootstrap/ButtonGroup";
import SessionCard from "../SessionCard";
import { useEffect, useState } from "react";
import { api } from "../utils/api";
import LoadingSpinner from "../Spinner/Spinner";
import ModalResult from "../ModalResult";

export default function Sessions({
  sessions,
  setSessions,
  onClickVote,
  closedVoting,
  setClosedVoting,
}) {
  const [isLoading, setIsLoading] = useState(false);
  const [showModalResult, setShowModalResult] = useState(false);
  const [topicResult, setTopicResult] = useState(null);

  useEffect(() => {
    setSessions([]);
    setIsLoading(true);
    api
      .get("sessions", {
        params: { showClosedSessions: closedVoting ? "Y" : "N" },
      })
      .then((result) => {
        setSessions(result.data);
        setIsLoading(false);
      });
  }, [closedVoting, setSessions]);

  async function getTopicResult(session) {
    const id = session?.topic?.id;

    await api.get(`topics/${id}/result`).then((result) => {
      setTopicResult(result.data);
    });
  }

  async function onClickButton(session) {
    if (closedVoting) {
      await getTopicResult(session);
      setShowModalResult(true);
    } else {
      onClickVote(session);
    }
  }

  return (
    <>
      <ModalResult
        showModalResult={showModalResult}
        setShowModalResult={setShowModalResult}
        topic={topicResult}
      />

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
          {!isLoading ? (
            sessions?.map((session) => (
              <SessionCard
                key={`${session.id}-session`}
                session={session}
                closedVoting={closedVoting}
                onClickButton={onClickButton}
              />
            ))
          ) : (
            <LoadingSpinner />
          )}
        </div>
      </section>
    </>
  );
}
