import React, { useEffect, useState } from "react";
import "./App.css";

function App() {
  const [tasks, setTasks] = useState([]);
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [reminderTime, setReminderTime] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [message, setMessage] = useState("");

  const API_URL = "http://localhost:8080/tasks";


  const fetchTasks = async () => {
    const response = await fetch(API_URL);
    const data = await response.json();
    setTasks(data);
  };

  useEffect(() => {
    fetchTasks();
  }, []);


  const addTask = async () => {
    if (!title || !reminderTime || !phoneNumber || !message) {
      alert("Please fill all required fields");
      return;
    }

    await fetch(API_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        title,
        description,
        reminderTime,
        phoneNumber,
        message,
        status: "PENDING"
      })
    });

  
    setTitle("");
    setDescription("");
    setReminderTime("");
    setPhoneNumber("");
    setMessage("");

    fetchTasks();
  };


  const deleteTask = async (id) => {
    await fetch(`${API_URL}/${id}`, {
      method: "DELETE"
    });

    fetchTasks();
  };

  return (
    <div className="container">
      <h1>Task Reminder</h1>

      <div className="form">
        <input
          type="text"
          placeholder="Task Title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />

        <input
          type="text"
          placeholder="Description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />

        <input
          type="datetime-local"
          value={reminderTime}
          onChange={(e) => setReminderTime(e.target.value)}
        />

        <input
          type="text"
          placeholder="Phone Number (+91XXXXXXXXXX)"
          value={phoneNumber}
          onChange={(e) => setPhoneNumber(e.target.value)}
        />

        <input
          type="text"
          placeholder="Message to Send"
          value={message}
          onChange={(e) => setMessage(e.target.value)}
        />

        <button onClick={addTask}>Add Task</button>
      </div>

      <div className="task-list">
        {tasks.map((task) => (
          <div key={task.id} className="task">
            <div>
              <h3>{task.title}</h3>
              <p>{task.description}</p>
              <p>Reminder: {task.reminderTime}</p>
              <p>Phone: {task.phoneNumber}</p>
              <p>Status: {task.status}</p>
            </div>

            <button onClick={() => deleteTask(task.id)}>Delete</button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;


