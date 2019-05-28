import React, { Component } from 'react';
import axios from 'axios'
type Transaction = {
  idTransaction: number,
  idUser: number,
  transactionDate: Date
}

type TransactionState = {
  transactions: Transaction[];
};

class Transactions extends Component<any, TransactionState>{
  state: TransactionState = {
    transactions: [],
  };

  async componentDidMount() {
    try {
      const resp = await axios.get('/transactions')
      const transactions: Transaction[] = resp.data;
      this.setState({ transactions: transactions })
      console.log(transactions)
    } catch (e) {
      console.log(e)
    }
  }

  render() {
    const { transactions: transactions } = this.state;
    console.log(transactions)
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
            {transactions.map((transaction) => {
              return (
                <tr key={transaction.idTransaction}>
                  <td>{transaction.idTransaction}</td>
                  <td>{transaction.idUser}</td>
                  <td>{transaction.transactionDate

                }</td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    )
  }
}

export default Transactions;
