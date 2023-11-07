import { useEffect } from "react";
import TopicCard from "../TopicCard";
import Button from "react-bootstrap/Button";
import { api } from "../utils/api";

export default function Topics({
  topics,
  setTopics,
  onClickStartTopic,
  onClickCreateTopic,
}) {
  useEffect(() => {
    api.get("topics").then((result) => {
      setTopics(result.data);
    });
  }, []);

  return (
    <section>
      <header className="align-end">
        <Button
          variant="success"
          className="me-2 mt-2"
          onClick={onClickCreateTopic}
        >
          Cadastrar uma pauta
        </Button>
      </header>

      <div className="cards-container">
        {topics?.map((topic) => (
          <TopicCard
            key={`${topic.id}-${topic.name}`}
            topic={topic}
            onClickStartTopic={onClickStartTopic}
          />
        ))}
      </div>
    </section>
  );
}
