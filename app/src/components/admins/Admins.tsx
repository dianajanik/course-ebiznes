import React, { Component } from 'react';
import axios from 'axios'
type Admin = {
  idAdmin: number,
  adminUsername: string,
  adminPassword: string
}

type AdminsState = {
  admins: Admin[];
};

class Admins extends Component<any, AdminsState>{
  state: AdminsState = {
    admins: [],
  };



  async componentDidMount() {
    try {
      const resp = await axios.get('/admins')
      const admins: Admin[] = resp.data;
      this.setState({ admins: admins })
      console.log(admins)
    } catch (e) {
      console.log(e)
    }
  }
  render() {
    const { admins: admins } = this.state;
    return (
      <div className="container">
        <table className="table">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Username</th>
              <th scope="col">Password</th>
            </tr>
          </thead>
          <tbody>
            {admins.map((admin) => {
              return (
                <tr key={admin.idAdmin}>
                  <td>{admin.idAdmin}</td>
                  <td>{admin.adminUsername}</td>
                  <td>{admin.adminPassword}</td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    )
  }
}

export default Admins;
