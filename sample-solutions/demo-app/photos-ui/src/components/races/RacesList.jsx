import React, { useState, useEffect } from 'react';
import api from '../../services/api';

const RacesList = () => {
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [items, setItems] = useState([]);

  useEffect(() => {
    api.getRaces()
      .then(res => res.json())
      .then(
        (result) => {
          setIsLoaded(true);
          setItems(result);
        },
        (error) => {
          setIsLoaded(true);
          setError(error);
        }
      )
  }, [])

  if (error) {
    return <div>Error: {error.message}</div>;
  } else if (!isLoaded) {
    return <div>Loading...</div>;
  } else {
    return (
      <div className='grid-item'>
        <h2>Races</h2>
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Species</th>
              <th>Name</th>
              <th>Description</th>
            </tr>
          </thead>
          <tbody>
            {items.map(item => (
              <tr key={item.id}>
                <td>{item.id}</td>
                <td>{item.speciesId}</td>
                <td>{item.name}</td>
                <td>{item.description}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}

export default RacesList;