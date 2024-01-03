
import axios from "axios";

export const api = axios.create(
    {
        baseURL : "http://localhost:8080"
    }
)


export const getAllTrainers =  ()=>
    api.get("/trainers/all-trainers")


export const addTrainer = (trainer)=>
    api.post("/trainers/trainer/add", trainer)

export const deleteTrainerById = (trainerId)=>

    api.delete(`/trainers/trainer/delete/${trainerId}`)

export const getTrainerById = (trainerId)=>
    api.get(`/trainers/trainer/${trainerId}`)

export const updateTrainerById = (trainerId, trainer)=>
    api.put(`/trainers/trainer/update/${trainerId}`, trainer)

export const getAllMemebers = ()=>
    api.get("/members/all-members")

export const deleteMemberById = (membershipId)=>
    api.delete(`/members/member/delete/${membershipId}`)

export const addMember = (member,trainerId)=>
    api.post(`/members/${trainerId}/add`, member)