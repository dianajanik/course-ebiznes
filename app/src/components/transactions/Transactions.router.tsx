import React from 'react';
import {
  BrowserRouter,
  Switch,
  Route,
} from 'react-router-dom';

import Transactions from './Transactions';
import CreateTransaction from './create/CreateTransaction';

const TransactionsRouter = () => {
  return (
    <BrowserRouter>
      <Switch>
        <Route exact path="/transactions" component={Transactions} />
        <Route path="/transactions/create" component={CreateTransaction} />
      </Switch>
    </BrowserRouter>
  );
}

export default TransactionsRouter;
