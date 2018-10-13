
    create table Alumno (
        idAlumno bigint not null auto_increment,
        apellido varchar(255),
        email varchar(255),
        githubUser varchar(255),
        legajo varchar(255),
        nombre varchar(255),
        secretCode varchar(255),
        primary key (idAlumno)
    )

    create table Asignacion (
        idAsignacion bigint not null auto_increment,
        idTarea bigint,
        idAlumno bigint,
        primary key (idAsignacion)
    )

    create table Tarea (
        idTarea bigint not null auto_increment,
        enunciado varchar(255),
        fechaLimiteDeEntrega datetime,
        primary key (idTarea)
    )

    alter table Asignacion 
        add constraint FK_mxa3dwfri37u0awq4qit5enak 
        foreign key (idTarea) 
        references Tarea (idTarea)

    alter table Asignacion 
        add constraint FK_d6w2cqxnm8plr3ujay37aowoy 
        foreign key (idAlumno) 
        references Alumno (idAlumno)
