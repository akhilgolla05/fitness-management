import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { getTrainerById, updateTrainerById } from '../utilities/ApiFunctions'

const UpdateTrainer = () => {

    const [trainer, setTrainer] = useState({
        firstName:"",
        lastName:"",
        email:"",
        specialization:"",
        experience:""
    })
    const {trainerId} = useParams()

    useEffect(()=>{

        retrieveTrainerById(trainerId)
    },[trainerId])

    const retrieveTrainerById = (trainerId)=>{
        
        getTrainerById(trainerId)
        .then((response)=>{
            // console.log(response.data)
            setTrainer(response.data)
        }).catch((error)=>{console.log(error)})
        

    }

    const handleInputChange = (event)=>{
        
        const value = event.target.value
        setTrainer({...trainer, [event.target.name] : value})
        // console.log(trainer)
    }

    const handleClear = ()=>{
        setTrainer({
            firstName:"",
            lastName:"",
            email:"",
            specialization:"",
            experience:""
        })
    }

    const navigate = useNavigate()

    const handleUpdateTrainer = ()=>{
        console.log(trainer)

        updateTrainerById(trainerId, trainer)
        .then((response)=>{
            console.log(response.data)
            navigate("/")
        }).catch((error)=>{
            console.log(error)
        })
    }

  return (
    
      <div className='max-w-xl border-b mx-auto my-6 shadow px-6 bg-slate-50'>
        <div className='my-4 w-full'>
            <p className='text-center text-lg font-serif '>Edit Trainer Information</p>
        </div>
      <div>
        <div className='w-full font-serif'>
        <label className=" block px-2 my-2 font-normal text-lg" 
        htmlFor='firstName'>First Name : </label>
        <input 
        id="firstName" 
        type="text" 
        name="firstName"
        value={trainer.firstName} 
        onChange={handleInputChange}
        className="block border px-2 mx-2 py-1"/>
        </div>

        <div className='w-full font-serif'>
        <label className=" block px-2 my-2 font-normal text-lg" 
        htmlFor='lastName'>Last Name : </label>
        <input 
        id="lastName" 
        type="text" 
        name="lastName"
        value={trainer.lastName} 
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
        value={trainer.email} 
        onChange={handleInputChange}
        className=" block border px-2 mx-2 py-1"/>
        </div>

        <div className='w-full font-serif'>
        <label className=" block px-2 my-2font-normal text-lg" 
        htmlFor='specialization'>Specilization : </label>
        <input 
        id="specialization" 
        type="text" 
        name="specialization"
        value={trainer.specialization} 
        onChange={handleInputChange}
        className="block border px-2 mx-2 py-1"/>
        </div>

        <div className='w-full font-serif'>
        <label className=" block px-2 my-2 font-normal text-lg" 
        htmlFor='experience'>Experience : </label>
        <input 
        id="experience" 
        type="number" 
        name="experience"
        value={trainer.experience} 
        onChange={handleInputChange}
        className="block border px-2 mx-2 py-1"/>
        </div>


        <div className='flex my-4 space-x-3'>
            <button onClick={handleClear} className='px-2 py-2 text-lg font-medium border rounded-md bg-red-300 hover:bg-red-500'>Clear</button>
            <button onClick={handleUpdateTrainer} className='px-2 py-2 text-lg font-medium rounded-md text-center border bg-green-300 hover:bg-green-600'>Update</button>
        </div>


      </div>
    </div>
    
  )
}

export default UpdateTrainer
