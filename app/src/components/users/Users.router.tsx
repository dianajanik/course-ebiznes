import React from 'react';
import {
  BrowserRouter,
  Switch,
  Route,
} from 'react-router-dom';

import Users from './Users';
import CreateUser from './create/CreateUser';

const ProductsRouter = () => {
  return (
    <BrowserRouter>
    <Switch>
      <Route exact path="/users" component={Users} />
      <Route path="/users/create" component={CreateUser} />
    </Switch>
  </BrowserRouter>
  );
}

export default ProductsRouter;
