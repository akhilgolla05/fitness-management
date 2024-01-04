import React, { useEffect, useState } from 'react'
import { deleteMemberById, getAllMemebers } from '../utilities/ApiFunctions'
import { useNavigate } from 'react-router-dom'

const MembersList = () => {

  const [membersData, setMembersData] = useState([])

useEffect(()=>{getMembers()},[])

const getMembers = async ()=>{

  
    getAllMemebers()
    .then((response)=>{
        // console.log(response.data)
        setMembersData(response.data)
    }).catch((error)=>{
        console.log(error)
    }).finally(()=>{console.log("finished....")})

}
const navigate = useNavigate()

const handleAddMember = ()=>{
    navigate("/add-member")
}

const handleDelete = (membershipId)=>{
    deleteMemberById(membershipId)
    .then((response)=>{
        // console.log(response)
        // console.log("deleted successfully")
        getMembers()

    }).catch((error)=>{console.log(error)})
}

const handleUpdate = (membershipId)=>{
    // console.log(`membershipId : ${membershipId}`)
    navigate(`/update-member/${membershipId}`)
}

  return (
    <div>
      <div className='max-w-4xl mx-auto mt-10 shadow'>

<div className=''>
    <button className='bg-green-700 hover:bg-green-900 text-white font-semibold px-3 py-3 rounded-md' onClick={handleAddMember}>Add Member</button>
</div>
<table className="table-fixed border min-w-full mt-2 px-3">
    <thead className='bg-slate-300'>
        <tr className='w-full'>
            <th className='text-left font-medium uppercase px-2 py-3 tracking-wider'>First Name</th>
            <th className='text-left font-medium uppercase px-4 py-4 tracking-wider'>Last Name</th>
            <th className='text-left font-medium uppercase px-4 py-4 tracking-wider'>Email</th>
            <th className='text-left font-medium uppercase px-4 py-4 tracking-wider'>Age</th>
            <th className='text-left font-medium uppercase px-4 py-4 tracking-wider'>Date Of Birth</th>
            <th className='text-left font-medium uppercase px-4 py-4 tracking-wider'>Joining Date</th>
            <th className='text-left font-medium uppercase px-4 py-4 tracking-wider'>Duration</th>
            <th className='text-left font-medium uppercase px-4 py-4 tracking-wider'>Actions</th>
        </tr>
        
    </thead>
    <tbody>
        {
            membersData.map((data)=>(

                <tr key={data.membershipId} className='hover:bg-slate-100'>
                    <td className='text-left px-2 py-2 font-normal'>{data.firstName}</td>
                    <td className='text-left px-2 py-2 font-normal'>{data.lastName}</td>
                    <td className='text-left px-2 py-2 font-normal'>{data.email}</td>
                    <td className='text-left px-2 py-2 font-normal'>{data.age}</td>
                    <td className='text-left px-2 py-2 font-normal'>{data.dateOfBirth}</td>
                    <td className='text-left px-2 py-2 font-normal'>{data.joiningDate}</td>
                    <td className='text-left px-2 py-2 font-normal'>{data.duration}</td>
                    <td className='flex text-left px-2 py-2 font-normal space-x-2'>
                        <button className='border bg-orange-400 px-2 py-2 rounded-md text-center font-mono hover:bg-orange-700 hover:text-white' onClick={()=>handleUpdate(data.membershipId)}>Update</button>
                        <button className='border bg-red-400 px-2 py-2 rounded-md font-mono text-center hover:text-white hover:bg-red-700' onClick={()=>handleDelete(data.membershipId)}>Delete</button>
                    </td>
                </tr>

            ))
        }
        
    </tbody>
</table>

</div>
    </div>
  )
}

export default MembersList
