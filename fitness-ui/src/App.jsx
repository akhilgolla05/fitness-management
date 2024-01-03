import { BrowserRouter, Route, Routes } from "react-router-dom"
import NavBar from "./components/commons/NavBar"
import TrainersList from "./components/trainer/TrainersList"
import AddTrainer from "./components/trainer/AddTrainer"
import UpdateTrainer from "./components/trainer/UpdateTrainer"
import Home from "./components/commons/Home"
import MembersList from "./components/member/MembersList"
import AddMember from "./components/member/AddMember"


function App() {
  

  return (
    <BrowserRouter>
      <NavBar/>
    <Routes>
      <Route path="/" element={<Home/>}/>
      <Route path="/trainers-list" element={<TrainersList/>}/>
      <Route path="/add-trainer" element={<AddTrainer/>}/>
      <Route path="/update-trainer/:trainerId" element={<UpdateTrainer/>}/>

      <Route path="/members-list" element={<MembersList/>}/>
      <Route path="/add-member" element={<AddMember/>}/>
      <Route/>
    </Routes>
      
      
    </BrowserRouter>
  )
}

export default App
