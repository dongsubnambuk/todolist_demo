import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Todo from './pages/todo';
import MainPage from './pages/MainPage';
import Login from './pages/Login';
import Signup from './pages/Signup';

function App() {  
  return (
    <Router>
      <div className="mobile-container">
        <Routes>
          <Route path="/todo" element={<Todo />} />
          <Route path="/" element={<MainPage />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
