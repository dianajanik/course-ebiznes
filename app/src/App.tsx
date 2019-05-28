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
import './App.scss';
import ProductsRouter from './components/products/Products.router'
import CategoriesRouter from './components/categories/Categories.router'
import UsersRouter from './components/users/Users.router'
import AdminsRouter from './components/admins/Admins.router'
import StockRouter from './components/stocks/Stocks.router'
axios.defaults.baseURL = 'http://localhost:9000'

const App = () => {
  return (
    <BrowserRouter>
    <Switch>
      <Route exact path="/" component={Home} />
      <Route path="/products" component={ProductsRouter} />
      <Route path="/categories" component={CategoriesRouter} />
      <Route path="/users" component={UsersRouter} />
      <Route path="/admins" component={AdminsRouter} />
      <Route path="/stocks" component={StockRouter} />
     
    </Switch>
  </BrowserRouter>
  );
}

export default App;
