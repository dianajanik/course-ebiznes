import React from 'react';
import {
  BrowserRouter,
  Switch,
  Route,
} from 'react-router-dom';

import Stocks from './Stocks';
import CreateStock from './create/CreateStock';

const StocksRouter = () => {
  return (
    <BrowserRouter>
    <Switch>
      <Route exact path="/stocks" component={Stocks} />
      <Route path="/stocks/create" component={CreateStock} />
    </Switch>
  </BrowserRouter>
  );
}

export default StocksRouter;
