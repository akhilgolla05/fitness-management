import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { addMember, getAllTrainers } from '../utilities/ApiFunctions'

const AddMember = () => {

    const [member, setMember] = useState({
        firstName:"",
        lastName:"",
        email:"",
        age:"",
        dateOfBirth:"",
        joiningDate:"",
        duration:""
    })

    const handleInputChange = (event)=>{
        
        const value = event.target.value
        setMember({...member, [event.target.name] : value})
        //console.log(member)
    }

    const navigate = useNavigate()

    const [trainerId, setTrainerId] = useState()

    const handleTrainerId = (event)=>{
        console.log("TrainerId : ")
        console.log(event.target.value)
        setTrainerId(event.target.value)

    }

    const handleSaveMember = async ()=>{
        addMember(member, trainerId)
        .then((response)=>{
            console.log(response.data)
            navigate("/members-list")
        })
        .catch((error)=>{
            console.log(error)
        })
        
    }

    const handleClear = ()=>{
        setMember({
            firstName:"",
            lastName:"",
            email:"",
            age:"",
            dateOfBirth:"",
            joiningDate:"",
            duration:""
        })
    }

    const [trainers, setTrainers] = useState([])

    useEffect(()=>{

        retrieveAllTrainers()
       
    },[])

    const retrieveAllTrainers = async ()=>{

        try{
            const response = await getAllTrainers()
            console.log(response.data)
            setTrainers(response.data)
            console.log(response.data)

        }catch(error){
            console.log(error)
        }

    }

    
    

  return (
    <div>
      <div className='max-w-xl border-b mx-auto my-6 shadow px-6 bg-slate-50'>
        <div className='my-4 w-full'>
            <p className='text-center text-lg font-serif '>Add Member Details</p>
        </div>
      <div>
        <div className='w-full font-serif'>
        <label className=" block px-2 my-2 font-normal text-lg" 
        htmlFor='firstName'>First Name : </label>
        <input 
        id="firstName" 
        type="text" 
        name="firstName"
        value={member.firstName} 
        onChange={handleInputChange}
        className="block border px-2 mx-2 py-1"/>
        </div>

        <div className='w-full font-serif'>
        <label className=" block px-2 my-2 font-normal text-lg" 
        htmlFor='lastName'>last Name : </label>
        <input 
        id="lastName" 
        type="text" 
        name="lastName"
        value={member.lastName} 
        onChange={handleInputChange}
        className=" block border px-2 mx-2 py-1"/>
        </div>

        <div className='w-full font-serif'>
        <label className=" block px-2 my-2 font-normal text-lg" 
        htmlFor='email'>Email : </label>
        <input 
        id="email" 
        type="email" 
        name="email"
        value={member.email} 
        onChange={handleInputChange}
        className=" block border px-2 mx-2 py-1"/>
        </div>

        <div className='w-full font-serif'>
        <label className=" block px-2 my-2font-normal text-lg" 
        htmlFor='specialization'>age : </label>
        <input 
        id="age" 
        type="number" 
        name="age"
        value={member.age} 
        onChange={handleInputChange}
        className="block border px-2 mx-2 py-1"/>
        </div>

        <div className='w-full font-serif'>
        <label className=" block px-2 my-2 font-normal text-lg" 
        htmlFor='experience'>Date Of Birth : </label>
        <input 
        id="dateOfBirth" 
        type="date" 
        name="dateOfBirth"
        value={member.dateOfBirth} 
        onChange={handleInputChange}
        className="block border px-2 mx-2 py-1"/>
        </div>

        <div className='w-full font-serif'>
        <label className=" block px-2 my-2 font-normal text-lg" 
        htmlFor='experience'>Joining Date : </label>
        <input 
        id="joiningDate" 
        type="date" 
        name="joiningDate"
        value={member.joiningDate} 
        onChange={handleInputChange}
        className="block border px-2 mx-2 py-1"/>
        </div>

        <div className='w-full font-serif'>
        <label className=" block px-2 my-2 font-normal text-lg" 
        htmlFor='experience'>Duration : </label>
        <input 
        id="duration" 
        type="number" 
        name="duration"
        value={member.duration} 
        onChange={handleInputChange}
        className="block border px-2 mx-2 py-1"/>
        </div>

        <div>
        <label htmlFor='trainer'>Select a Trainer:</label>
        <select name="trainerId" id="trainer" onChange={handleTrainerId}>
        {
            trainers.map((trainer)=>(
                <option key={trainer.trainerId} value={trainer.trainerId}>{trainer.firstName} - {trainer.specialization}</option>
            ))
        }
        </select>
        
        {/* <select name="trainer" id="trainer">
            
            <option value="saab">Saab</option>
            <option value="opel">Opel</option>
            <option value="audi">Audi</option>
        </select> */}


        </div>



        <div className='my-4 space-x-3'>
            <button onClick={handleClear} className='px-2 py-2 text-lg font-medium border rounded-md bg-red-300 hover:bg-red-500'>Clear</button>
            <button onClick={handleSaveMember}  className='px-2 py-2 text-lg font-medium rounded-md text-center border bg-green-300 hover:bg-green-600'>Save</button>
        </div>


      </div>
    </div>
    </div>
  )
}

export default AddMember
