import { useState } from "react";
import Tab from "react-bootstrap/Tab";
import Tabs from "react-bootstrap/Tabs";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { api } from "./utils/api";
import LoginForm from "./Form";
import Topics from "./Topics";
import Sessions from "./Sessions";
import CenteredModal from "./Modal";
import ModalCreateTopic from "./ModalCreateTopic";
import ModalVote from "./ModalVote";
import "./App.css";

export default function App() {
  const [cpf, setCpf] = useState("");
  const [isLogged, setIsLogged] = useState(false);
  const [showModalStartVoting, setShowModalStartVoting] = useState(false);
  const [showModalCreateTopic, setShowModalCreateTopic] = useState(false);
  const [showModalVote, setShowModalVote] = useState(false);
  const [selectedTopic, setSelectedTopic] = useState(null);
  const [selectedSession, setSelectedSession] = useState(null);
  const [votingMinutes, setVotingMinutes] = useState(1);
  const [newTopic, setNewTopic] = useState("");
  const [topics, setTopics] = useState([]);
  const [sessions, setSessions] = useState([]);
  const [topicVote, setTopicVote] = useState("");
  const [closedVoting, setClosedVoting] = useState(false);

  function handleCpf(e) {
    setCpf(e.target.value);
  }

  function onSubmit() {
    if (cpf && cpf.length > 0) {
      setIsLogged(true);
    }
  }

  function onClickStartTopic(topic, hide) {
    setSelectedTopic(topic);
    setShowModalStartVoting(hide);
  }

  async function startVoting() {
    const config = {
      params: {
        minutesVoting:
          votingMinutes && Number(votingMinutes) && Number(votingMinutes) > 0
            ? Number(votingMinutes)
            : 1,
      },
    };
    await api
      .post(`sessions/${selectedTopic.id}/start-voting-session`, {}, config)
      .then(() => {
        setClosedVoting(false);
        getAllSessions();
        setShowModalStartVoting(false);
        toast.success("Votação iniciada com sucesso!");
      })
      .catch((error) => {
        setShowModalStartVoting(false);
        toast.error(error?.response?.data?.message);
      });
  }

  async function onCreateNewTopic() {
    await api
      .post("topics", { name: newTopic })
      .then(() => {
        getAllTopics();
        setShowModalCreateTopic(false);
        toast.success("Pauta criada com sucesso!");
      })
      .catch((error) => {
        setShowModalCreateTopic(false);
        toast.error(error?.response?.data?.message);
      });
    setNewTopic("");
  }

  function getAllTopics() {
    api.get("topics").then((result) => {
      setTopics(result.data);
    });
  }
  function getAllSessions() {
    api
      .get("sessions", {
        params: { showClosedSessions: closedVoting ? "Y" : "N" },
      })
      .then((result) => {
        setSessions(result.data);
      });
  }
  async function onSubmitVote() {
    const topicId = selectedSession?.topic?.id;
    await api
      .post(`votes/${topicId}/vote`, { cpfVoter: cpf, voteMessage: topicVote })
      .then(() => {
        setShowModalVote(false);
        toast.success("Voto registrado com sucesso!");
      })
      .catch((error) => {
        setShowModalVote(false);
        toast.error(error?.response?.data?.message);
      });
  }

  function onClickVote(sessionSelected) {
    setSelectedSession(sessionSelected);
    setShowModalVote(true);
  }

  return (
    <>
      <ToastContainer
        position="top-right"
        autoClose={5000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme="light"
      />
      <main id="container">
        <CenteredModal
          show={showModalStartVoting}
          onHide={() => setShowModalStartVoting(false)}
        >
          <Modal.Header closeButton>
            <Modal.Title id="contained-modal-title-vcenter">
              Iniciar votação?
            </Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Form>
              <Form.Group
                className="mb-3"
                controlId="exampleForm.ControlInput1"
              >
                <Form.Label>
                  Quantos minutos a votação deve ficar aberta?
                </Form.Label>
                <Form.Control
                  type="number"
                  placeholder="minutos de votação"
                  autoFocus
                  value={votingMinutes}
                  onChange={(e) => setVotingMinutes(e.target.value)}
                />
              </Form.Group>
            </Form>
          </Modal.Body>
          <Modal.Footer>
            <Button
              variant="secondary"
              onClick={() => setShowModalStartVoting(false)}
            >
              Close
            </Button>
            <Button variant="primary" onClick={startVoting}>
              Iniciar votação
            </Button>
          </Modal.Footer>
        </CenteredModal>

        <ModalCreateTopic
          showModalCreateTopic={showModalCreateTopic}
          setShowModalCreateTopic={setShowModalCreateTopic}
          newTopic={newTopic}
          setNewTopic={setNewTopic}
          onCreateNewTopic={onCreateNewTopic}
        />

        <ModalVote
          showModalVote={showModalVote}
          setShowModalVote={setShowModalVote}
          onSubmitVote={onSubmitVote}
          topic={selectedSession?.topic?.name}
          topicVote={topicVote}
          setTopicVote={setTopicVote}
        />

        <div id="wrapper">
          {isLogged ? (
            <Tabs
              defaultActiveKey="topic"
              transition={false}
              id="noanim-tab-example"
              className="mb-3"
            >
              <Tab eventKey="topic" title="Pautas">
                <Topics
                  topics={topics}
                  setTopics={setTopics}
                  onClickStartTopic={onClickStartTopic}
                  onClickCreateTopic={() => setShowModalCreateTopic(true)}
                />
              </Tab>
              <Tab eventKey="session" title="Sessões">
                <Sessions
                  sessions={sessions}
                  setSessions={setSessions}
                  onClickVote={onClickVote}
                  closedVoting={closedVoting}
                  setClosedVoting={setClosedVoting}
                />
              </Tab>
            </Tabs>
          ) : (
            <LoginForm cpf={cpf} handleCpf={handleCpf} onSubmit={onSubmit} />
          )}
        </div>
      </main>
    </>
  );
}
