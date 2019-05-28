import React from 'react';
import {
  BrowserRouter,
  Switch,
  Route,
} from 'react-router-dom';

import Categories from './Catogories';
import CreateCategory from './create/CreateCategory';

const CategoriesRouter = () => {
  return (
    <BrowserRouter>
    <Switch>
      <Route exact path="/categories" component={Categories} />
      <Route path="/categories/create" component={CreateCategory} />
    </Switch>
  </BrowserRouter>
  );
}

export default CategoriesRouter;
