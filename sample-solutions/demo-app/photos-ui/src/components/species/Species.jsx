import React from 'react';
import SpeciesAdd from './SpeciesAdd';
import SpeciesList from './SpeciesList';

const Species = () => {
  return (
    <div className='grid-container'>
      <SpeciesList/>
      <SpeciesAdd />
    </div>
  )
}

export default Species;