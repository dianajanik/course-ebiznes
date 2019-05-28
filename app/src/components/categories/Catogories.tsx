import React, { Component } from 'react';
import axios from 'axios'
type Category = {
  idCategory: number,
  categoryName: string,
  categoryUpper: number
}

type CategoriesState = {
  categories: Category[];
};

class Categories extends Component<any, CategoriesState>{
  state: CategoriesState = {
    categories: [],
  };

  async componentDidMount() {
    try {
      const resp = await axios.get('/categories')
      const categories: Category[] = resp.data;
      this.setState({ categories })
      console.log(categories)
    } catch (e) {
      console.log(e)
    }
  }

  render() {
    const { categories } = this.state;
    console.log(categories)
    return (
      <div className="container">
        <table className="table">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Name</th>
              <th scope="col">Upper</th>
            </tr>
          </thead>
          <tbody>
            {categories.map((category) => {
              return (
                <tr key={category.idCategory}>
                  <td>{category.idCategory}</td>
                  <td>{category.categoryName}</td>
                  <td>{category.categoryUpper}</td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    )
  }
}

export default Categories;
