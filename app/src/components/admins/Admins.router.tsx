import React from 'react';
import {
  BrowserRouter,
  Switch,
  Route,
} from 'react-router-dom';

import Admins from './Admins';
import CreateAdmin from './create/CreateAdmin';

const AdminsRouter = () => {
  return (
    <BrowserRouter>
    <Switch>
      <Route exact path="/admins" component={Admins} />
      <Route path="/admins/create" component={CreateAdmin} />
    </Switch>
  </BrowserRouter>
  );
}

export default AdminsRouter;
