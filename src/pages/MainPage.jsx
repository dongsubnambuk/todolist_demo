import React from 'react';
import '../CSS/MainPage.css';
import logo from'../images/todologo.png';
import { useNavigate } from 'react-router-dom';
const MainPage = () => {
    const navigate = useNavigate();

    const handleLoginClick = () => {
        navigate('/login');
    };

    const handleSignupClick = () => {
        navigate('/signup');
    };




return (
  <div className='main-page-container'>

    <div className='main-page-title-container'>
        <img className='main-page-title-logo' src={logo} alt='logo'/>
        <p className='main-page-title-content'>기깔나는 todo와 함께 <br/> 일상을 기록해보세요.</p>
    </div>

    <div className='main-page-btn-container'>
        <button className='main-page-signup-btn' onClick={handleSignupClick}>
            계정없냐? 회원가입 ㄱ
        </button>
        <button className='main-page-login-btn' onClick={handleLoginClick}>
             계정이 있냐? 로그인 ㄱ
        </button>
    </div>
  </div>

    );
}

export default MainPage;