import React, { Component } from 'react';
import axios from 'axios'
type Stock = {
  idStock: number,
  idProduct: number,
  quantity: number
}

type StockState = {
  stocks: Stock[];
};

class Stocks extends Component<any, StockState>{
  state: StockState = {
    stocks: [],
  };

  async componentDidMount() {
    try {
      const resp = await axios.get('/stocks')
      const stocks: Stock[] = resp.data;
      this.setState({ stocks: stocks })
      console.log(stocks)
    } catch (e) {
      console.log(e)
    }
  }

  render() {
    const { stocks: stocks } = this.state;
    console.log(stocks)
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
            {stocks.map((stock) => {
              return (
                <tr key={stock.idStock}>
                  <td>{stock.idStock}</td>
                  <td>{stock.idProduct}</td>
                  <td>{stock.quantity}</td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    )
  }
}

export default Stocks;
