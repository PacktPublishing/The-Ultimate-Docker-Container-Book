import React, { useState, useEffect } from 'react';

function PhotoGallery() {
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [items, setItems] = useState([]);

  // Note: the empty deps array [] means
  // this useEffect will run once
  // similar to componentDidMount()
  useEffect(() => {
    fetch("http://localhost:4000/photos")
      .then(res => res.json())
      .then(
        (result) => {
          setIsLoaded(true);
          setItems(result);
        },
        // Note: it's important to handle errors here
        // instead of a catch() block so that we don't swallow
        // exceptions from actual bugs in components.
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
      <div>
        <h2>Photo Gallery</h2>
        <div className='container'>
          {items.map(item => (
            <div className='item' key={item.id}>
              <div>
                <img src={`http://localhost:9000/${item.url}`} title={item.url} alt=""/>
              </div>
              <div>
                <div><em>Title:</em> {item.title}</div> 
                <div><em>Description:</em> {item.description}</div>
                <div><em>Species:</em> {item.speciesId}</div>
                <div><em>Race:</em> {item.raceId}</div>
              </div>
            </div>
          ))}
        </div>
      </div>
    );
  }
}

export default PhotoGallery;