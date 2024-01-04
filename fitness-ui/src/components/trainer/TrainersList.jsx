import React, { useEffect, useState } from 'react'
import { deleteTrainerById, getAllTrainers } from '../utilities/ApiFunctions'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'

const TrainersList = () => {

    const [trainersData, setTrainersData] = useState([])

    useEffect(()=>{getTrainers()},[])

    const getTrainers = async ()=>{

        // axios.get("http://localhost:8080/trainers/all-trainers")
        // .then((response)=>{
        //     console.log(response.data)
        //     setTrainersData(response.data)
        // }).catch((error)=>{
        //     console.log(error)
        // }).finally(()=>{console.log("finished....")})

        getAllTrainers()
        .then((response)=>{
            // console.log(response.data)
            setTrainersData(response.data)
        }).catch((error)=>{
            console.log(error)
        }).finally(()=>{console.log("finished....")})

    }
    const navigate = useNavigate()
    const handleAddTrainer = ()=>{
        navigate("/add-trainer")
    }

    const handleDelete = (trainerId)=>{
        deleteTrainerById(trainerId)
        .then((response)=>{
            // console.log(response)
            // console.log("deleted successfully")
            getTrainers()

        }).catch((error)=>{console.log(error)})
    }

    const handleUpdate = (trainerId)=>{
        navigate(`/update-trainer/${trainerId}`)
    }

  return (
    <div className='max-w-4xl mx-auto mt-10 shadow'>

        <div className=''>
            <button className='bg-green-700 hover:bg-green-900 text-white font-semibold px-3 py-3 rounded-md' onClick={handleAddTrainer}>Add Trainer</button>
        </div>
        <table className="table-fixed border min-w-full mt-2 px-3">
            <thead className='bg-slate-300'>
                <tr className='w-full'>
                    <th className='text-left font-medium uppercase px-2 py-3 tracking-wider'>First Name</th>
                    <th className='text-left font-medium uppercase px-4 py-4 tracking-wider'>Last Name</th>
                    <th className='text-left font-medium uppercase px-4 py-4 tracking-wider'>Email</th>
                    <th className='text-left font-medium uppercase px-4 py-4 tracking-wider'>Specialization</th>
                    <th className='text-left font-medium uppercase px-4 py-4 tracking-wider'>Experience</th>
                    <th className='text-left font-medium uppercase px-4 py-4 tracking-wider'>Actions</th>
                </tr>
                
            </thead>
            <tbody>
                {
                    trainersData.map((data)=>(

                        <tr key={data.trainerId} className='hover:bg-slate-100'>
                            <td className='text-left px-2 py-2 font-normal'>{data.firstName}</td>
                            <td className='text-left px-2 py-2 font-normal'>{data.lastName}</td>
                            <td className='text-left px-2 py-2 font-normal'>{data.email}</td>
                            <td className='text-left px-2 py-2 font-normal'>{data.specialization}</td>
                            <td className='text-left px-2 py-2 font-normal'>{data.experience}</td>
                            <td className='flex text-left px-2 py-2 font-normal space-x-2'>
                                <button className='border bg-orange-400 px-2 py-2 rounded-md text-center font-mono hover:bg-orange-700 hover:text-white' onClick={()=>handleUpdate(data.trainerId)}>Update</button>
                                <button className='border bg-red-400 px-2 py-2 rounded-md font-mono text-center hover:text-white hover:bg-red-700' onClick={()=>handleDelete(data.trainerId)}>Delete</button>
                            </td>
                        </tr>

                    ))
                }
                
            </tbody>
        </table>
      
    </div>
  )
}

export default TrainersList
