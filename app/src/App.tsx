import React from 'react';
import logo from './logo.svg';
import './App.scss';
import axios from 'axios'
import {
  BrowserRouter,
  Switch,
  Route,
} from 'react-router-dom';

import Home from './components/home/Home';
import Products from './components/products/Products';
import './App.scss';
import ProductsRouter from './components/products/Products.router'
axios.defaults.baseURL = 'http://localhost:9000'

const App = () => {
  return (
    <BrowserRouter>
    <Switch>
      <Route exact path="/" component={Home} />
      <Route exact path="/products" component={ProductsRouter} />
     
    </Switch>
  </BrowserRouter>
  );
}

export default App;
