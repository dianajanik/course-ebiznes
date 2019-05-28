import React from 'react';
import {
  BrowserRouter,
  Switch,
  Route,
} from 'react-router-dom';

import Subtransactions from './Subtransactions';
import CreateSubtransaction from './create/CreateSubtransactions';

const SubtransactionsRouter = () => {
  return (
    <BrowserRouter>
    <Switch>
      <Route exact path="/subtransactions" component={Subtransactions} />
      <Route path="/subtransactions/create" component={CreateSubtransaction} />
    </Switch>
  </BrowserRouter>
  );
}

export default SubtransactionsRouter;
