import { BrowserRouter, Route, Routes } from "react-router-dom"
import NavBar from "./components/commons/NavBar"
import TrainersList from "./components/trainer/TrainersList"
import AddTrainer from "./components/trainer/AddTrainer"
import UpdateTrainer from "./components/trainer/UpdateTrainer"


function App() {
  

  return (
    <BrowserRouter>
      <NavBar/>
    <Routes>
      <Route path="/" element={<TrainersList/>}/>
      <Route path="/add-trainer" element={<AddTrainer/>}/>
      <Route path="/update-trainer/:trainerId" element={<UpdateTrainer/>}/>
      <Route/>
    </Routes>
      
      
    </BrowserRouter>
  )
}

export default App
