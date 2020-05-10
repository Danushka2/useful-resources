/*----------------Trigger for randomly generated date column--------------------*/

Create trigger add_randomDate_trigger
On FactInventoryWithDate
Instead of Insert, Update
As
Begin
    DECLARE	@inventory_id numeric(18,0)
    DECLARE	@quantity numeric(18,0)
    DECLARE	@is_spare varchar(50)
    DECLARE	@version numeric(18,0)
    DECLARE	@parts_sk numeric(18,0)
    DECLARE	@color_sk numeric(18,0)
    DECLARE	@set_sk numeric(18,0)
    DECLARE	@recorded_date int
    DECLARE @insertDate varchar(20)
    DECLARE @RandomDate datetime
    DECLARE @fromDate datetime='2018-04-07'

    SELECT @RandomDate= (DATEADD(day, ROUND(DATEDIFF(day, @fromDate, @fromDate) 
    * RAND(CHECKSUM(NEWID())), 5),DATEADD(second, abs(CHECKSUM(NEWID())) % 63072000, 
    @fromDate))) 

    /*---change the 112 to create another date format---*/
    select @insertDate = CONVERT(varchar(20), @RandomDate, 112);
    SELECT @recorded_date = convert(int, @insertDate)

    select @inventory_id = inventory_id, @quantity = quantity, @is_spare = is_spare, @version = version, @parts_sk = parts_sk, @color_sk = colors_sk, @set_sk = set_sk
    from inserted;

    Insert into FactInventoryWithDate(inventory_id, quantity, is_spare, version, parts_sk, colors_sk, set_sk, recorded_date) 
    values(@inventory_id, @quantity, @is_spare, @version, @parts_sk, @color_sk, @set_sk, @recorded_date)
end



drop trigger add_randomDate_trigger
truncate table FactInventoryWithDate;

select count(*)
from FactInventoryWithDate;

select *
from FactInventoryWithDate;



/*------------dummy date----------*/
Insert into FactInventoryWithDate(inventory_id, quantity, is_spare, version, parts_sk, colors_sk, set_sk) 
values(23, 1, 'yes', 1, 11, 12, 2)
Insert into FactInventoryWithDate(inventory_id, quantity, is_spare, version, parts_sk, colors_sk, set_sk) 
values(23, 1, 'yes', 1, 11, 12, 2)
Insert into FactInventoryWithDate(inventory_id, quantity, is_spare, version, parts_sk, colors_sk, set_sk) 
values(23, 1, 'yes', 1, 11, 12, 2)
Insert into FactInventoryWithDate(inventory_id, quantity, is_spare, version, parts_sk, colors_sk, set_sk) 
values(23, 1, 'yes', 1, 11, 12, 2)
