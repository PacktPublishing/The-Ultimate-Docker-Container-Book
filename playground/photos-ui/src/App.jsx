import './App.css';
import Races from './components/races/Races';
import SpeciesList from './components/species/SpeciesList';
import UploadPhoto from './components/photos/UploadPhoto';
import PhotoGallery from './components/photos/PhotosGallery';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>Animal Photos</h1>
      </header>
      <PhotoGallery/>
      <UploadPhoto />
      <SpeciesList/>
      <Races/>
    </div>
  );
}

export default App;
