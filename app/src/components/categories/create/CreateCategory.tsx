import React, { Component, FormEvent } from 'react';
import axios from 'axios'
import { interfaceDeclaration } from '@babel/types';

type CreateCategoryState = {
  categoryName: string,
  categoryUpper: string
};

class CreateCategory extends Component<any, CreateCategoryState>{
  state: CreateCategoryState = {
    categoryName: '',
    categoryUpper: ''
  };
  componentDidMount(){
    console.log("here")
  }
  render() {
    const {
      categoryName,
      categoryUpper
    } = this.state;

    return (
      <div className="container">
      <form onSubmit={this.submitFunction.bind(this)}>
          <div className="form-group">
            <input type="text" value={categoryName} className="form-control" id="categoryInput" placeholder="Category name"
             onChange={(e) => { this.setState({ categoryName: e.target.value})}}/>
          </div>
          <div className="form-group">
            <input type="text" value={categoryUpper} className="form-control" id="nameInput" placeholder="Category upper" 
            onChange={(e) => { this.setState({ categoryUpper: e.target.value})}} />
          </div>
          <button type="submit" className="btn btn-primary">Submit</button>
        </form>
      </div>
      )
  }

async submitFunction(e: FormEvent) {
    e.preventDefault();
    try {
      if (
        this.state.categoryName !== null
      ) {
        const data = {
          categoryName: this.state.categoryName,
          categoryUpper: parseInt(this.state.categoryUpper),
        };
        
        console.log(data);
        
        await axios.post(
          '/categories',
          data,
          {
            headers:{
              'Content-Type': 'application/json'
            }
          }
        );
      }
    } catch (error) {
      console.log(error);
    }
  }
}
       
export default CreateCategory;
