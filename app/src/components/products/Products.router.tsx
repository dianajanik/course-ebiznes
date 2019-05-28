import React from 'react';

import './App.scss';
import axios from 'axios'
import {
  BrowserRouter,
  Switch,
  Route,
} from 'react-router-dom';

import Products from './create/CreateProduct';
import CreateProduct from './create/CreateProduct';

const ProductsRouter = () => {
  return (
    <BrowserRouter>
    <Switch>
      <Route exact path="/products" component={Products} />
      <Route exact path="/products/create" component={CreateProduct} />
     
    </Switch>
  </BrowserRouter>
  );
}

export default ProductsRouter;
