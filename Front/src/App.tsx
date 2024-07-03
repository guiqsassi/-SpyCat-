import React from 'react';
import {BrowserRouter, Routes, Route, Outlet} from "react-router-dom"
import './App.css';
import Login from './Pages/Login/Login';
import LandingPage from './Pages/LandingPage/LandingPage';
import Cadastro from './Pages/Cadastro/Cadastro';
import Home from './Pages/Home/Home';
import AddPet from './Pages/AddPet/AddPet';
import PetsPage from './Pages/Pets/PetsPage';
import Suporte from './Pages/Suporte/Suporte';
import User from './Pages/User/User';
import OngCad from './Pages/OngCad/OngCad';
import ForgetPassword from './Pages/ForgetPassword/ForgetPassword';
import ChangePassword from './Pages/ChangeForgetPassword/ChangePassword';

function App() {
  return (
    <BrowserRouter>
      
      <Routes>
        <Route path='/' element={<LandingPage/>}/>
        <Route path='/Cadastro' element={<Cadastro/>}/>
        <Route path='/ChangePassword' element={<ChangePassword/>}/>
        <Route path='/forget-password' element={<ForgetPassword/>}/>
        <Route path='/Login' element={<Login/>}/>
        <Route path='/Home' element={<Home/>}/>
        <Route path='/Pets' element={<PetsPage/>}/>
        <Route path='/AddPet' element={<AddPet/>}/>
        <Route path='/Suporte' element={<Suporte/>}/>
        <Route path='/User' element={<User/>}/>
        <Route path='/OngCad' element={<OngCad/>}></Route>
      </Routes>

    </BrowserRouter>
  );
}

export default App;
