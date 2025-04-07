import simpy
import random
import statistics

random.seed(42)

# Recursos disponibles
enfermeras = 5
doctores = 3
laboratorios = 1
rayos_x = 1

# Tiempos por etapa (en minutos)
t_triage = 7
t_doctor = 20
t_lab = 30
t_rayos = 25

# Cada cuánto llega un paciente (en minutos)
llegada_paciente = 10

# Función que corre una simulación
def correr_simulacion(num_pacientes, extra_rayos=0):
    env = simpy.Environment()
    tiempos = []

    # Recursos
    enf = simpy.Resource(env, capacity=enfermeras)
    doc = simpy.PriorityResource(env, capacity=doctores)
    lab = simpy.PriorityResource(env, capacity=laboratorios)
    rayos = simpy.PriorityResource(env, capacity=rayos_x + extra_rayos)

    def atender(i):
        tiempo_inicio = env.now

        # TRIAGE
        with enf.request() as turno:
            yield turno
            yield env.timeout(t_triage)
            prioridad = random.randint(1, 5)

        # DOCTOR
        with doc.request(priority=prioridad) as turno:
            yield turno
            yield env.timeout(t_doctor)

        # LABORATORIO
        with lab.request(priority=prioridad) as turno:
            yield turno
            yield env.timeout(t_lab)

        # RAYOS X
        with rayos.request(priority=prioridad) as turno:
            yield turno
            yield env.timeout(t_rayos)

        tiempo_total = env.now - tiempo_inicio
        tiempos.append(tiempo_total)

    def llegar_pacientes():
        for i in range(num_pacientes):
            yield env.timeout(random.expovariate(1.0 / llegada_paciente))
            env.process(atender(i))

    env.process(llegar_pacientes())
    env.run()
    return round(statistics.mean(tiempos), 2)

# Resultados
tiempo_dia_normal = correr_simulacion(100)
tiempo_fin_semana = correr_simulacion(150)
tiempo_mejorado = correr_simulacion(150, extra_rayos=1)

# Mostrar resultados
print("Resultados de la simulación:")
print("Día normal (100 pacientes):", tiempo_dia_normal, "minutos")
print("Fin de semana (150 pacientes):", tiempo_fin_semana, "minutos")
print("Fin de semana mejorado (150 pacientes, 1 rayos X extra):", tiempo_mejorado, "minutos")
