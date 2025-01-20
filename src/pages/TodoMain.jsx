import React from 'react';
import { Avatar } from 'antd';
import { UserOutlined } from '@ant-design/icons';
import logo from '../images/todologo.png';
import '../CSS/TodoMain.css';

const TodoMain = () => {
    const introduction = "멋진 일을 함께 만들어가요!";
    const name = "기깔남";

    

    return (
        <div className='todomain-container'>
        
            <div className='todomain-logo-con'>
                <img className='todomain-logo' src={logo} alt='logo' />
                <h1 className='todomain-title'>기깔나는 Todo</h1>
            </div>

       
            <div className='todomain-introduction-container'>
                <div className='todomain-profile'>
                    <Avatar size={64} icon={<UserOutlined />} />
                    <div className='todomain-profile-info'>
                        <p className='todomain-introduction-name'>{name}님</p>
                        <p className='todomain-introduction-text'>{introduction}</p>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default TodoMain;
