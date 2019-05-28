import React, { Component } from 'react';
import axios from 'axios'
type Subtransaction = {
  idSubtransaction: number,
  idProduct: number,
  subtransactionQuantity: number
}

type SubtransactionState = {
  subtransactions: Subtransaction[];
};

class Subtransactions extends Component<any, SubtransactionState>{
  state: SubtransactionState = {
    subtransactions: [],
  };

  async componentDidMount() {
    try {
      const resp = await axios.get('/subtransactions')
      const subtransactions: Subtransaction[] = resp.data;
      this.setState({subtransactions })
      console.log(subtransactions)
    } catch (e) {
      console.log(e)
    }
  }

  render() {
    const { subtransactions } = this.state;
    console.log(subtransactions)
    return (
      <div className="container">
        <table className="table">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Product id </th>
              <th scope="col">Quantity</th>
            </tr>
          </thead>
          <tbody>
            {subtransactions.map((subtransaction) => {
              return (
                <tr key={subtransaction.idSubtransaction}>
                  <td>{subtransaction.idSubtransaction}</td>
                  <td>{subtransaction.idProduct}</td>
                  <td>{subtransaction.subtransactionQuantity}</td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    )
  }
}

export default Subtransactions;
