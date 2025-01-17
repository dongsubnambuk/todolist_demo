import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Todo from './pages/todo';
import MainPage from './pages/MainPage';
function App() {
  return (
    <Router>
      <div className="mobile-container">
        <Routes>
          <Route path="/todo" element={<Todo />} />
          <Route path="/" element={<MainPage />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
