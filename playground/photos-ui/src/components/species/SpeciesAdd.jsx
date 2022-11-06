import React, {useState} from 'react';
import api from '../../services/api';

const SpeciesAdd = () => {
  const [species, setSpecies] = useState({});

  const onAdd = async (e) => {
    const res = await api.postSpecies(species);
    if(res.status === 200){
      setSpecies({});
    }
  }

  const onNameChanged = (e) => {
    setSpecies(prevState => ({ 
      ...prevState,
      name: e.target.value
    }));
  }

  const onDescriptionChanged = (e) => {
    setSpecies(prevState => ({ 
      ...prevState,
      description: e.target.value
    }));
  }

  return (
    <div className='grid-item'>
      <h2>New Species</h2>
      <div>Name: <input type="text" placeholder='Species Name' onChange={onNameChanged}/></div>
      <div>Description: <textarea placeholder='Description' onChange={onDescriptionChanged}/></div>
      <button onClick={onAdd}>Add</button>
    </div>
  ); 
}

export default SpeciesAdd;