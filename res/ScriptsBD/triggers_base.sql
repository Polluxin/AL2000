-- 5 Locations à la fois
create or replace trigger Trigger_Max5Locations
    before insert on LESLOCATIONS
    for each row
declare
    nb integer;
begin
    select count(*) into nb from LESLOCATIONS where IDCARTE=:NEW.IDCARTE AND ETAT='ENCOURS';
    if (nb > 5) then raise_application_error(-20000, 'Impossible de louer plus de 5 films');
    end if;
end;